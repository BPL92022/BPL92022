package terminal

import java.util.NoSuchElementException


class FileSystem {
  //key = command;  value = how many paths need to be specified
  val commandsAndNoOfPaths = Map("pwd" -> 0, "mkdir" -> 1, "ls" -> 0, "h" -> 0, "q" -> 0 )

  var rootPath : Folders = _
  var currentLevel : Folders = _
  var cmdPrefix : String = ""
  var path1_from : String = ""
  var path2_to : String = ""



  def run() : Unit = {
    //initialize the root
    rootPath = new Folders("")
    rootPath.currentPath = "/"
    currentLevel = rootPath

    //run the terminal continuously until is chosen q to quit
    commandInterpretor()
  }


  def commandInterpretor() : Unit = {
    print( """Your cmd (press "q" to quit, "h" for help): """ )
    val keyboardInput = getKeyboardInput

    splitToCmdAndPaths( keyboardInput )

    cmdPrefix match {
      case "pwd"        => println( getCurrentDirectoryPath ); unitializeCmdAndPaths(); commandInterpretor()
      case "mkdir"      => if(path1_from.nonEmpty) makeFolder(); unitializeCmdAndPaths(); commandInterpretor()
      case "ls"         => listAllFilesAndFolders(); unitializeCmdAndPaths(); commandInterpretor()
      case "h"          => help(); unitializeCmdAndPaths(); commandInterpretor()
      case "q"          => println("You chose q, the app will close.")
      case _            => println( "Command not recognized." ); commandInterpretor()     //this default case will keep the "terminal" running even in case if is a command not recognized
    }
  }

  def getKeyboardInput : String = scala.io.StdIn.readLine()

  def getCurrentDirectoryPath : String = currentLevel.currentPath

  def help() : Unit = {
    println(
      """Can be used the following commands:
        |pwd
        |mkdir
        |ls""".stripMargin)
  }

  def splitToCmdAndPaths( inputKeyboard : String ) : Unit = {
    try{
      //find the command; handle the case if the command does not exist
      this.cmdPrefix = commandsAndNoOfPaths.filter(
        x => inputKeyboard.startsWith(x._1)
      ).keys.head    //return into cmdPrefix the first element (and the only element) of the List which met the condition "inputKeyboard.startsWith( x )"


      //find the path or paths if the command support them; Ex: mkdir support one path; cp support 2 paths
      commandsAndNoOfPaths( this.cmdPrefix ) match {
        case 0 =>

        case 1 =>
          val splittedPaths : Array[String] = inputKeyboard.split( " " )

          if( splittedPaths.length == 1 ) throw new IllegalArgumentException
          if( splittedPaths.length > 2 ) throw new RuntimeException


          this.path1_from = splittedPaths(1)

        case 2 =>
          val splittedPaths : Array[String] = inputKeyboard.split( " " )


          if( splittedPaths.length > 3 ) throw new RuntimeException

          this.path1_from = splittedPaths(1)
          this.path2_to = splittedPaths(2)
      }
    }catch {
      case e: NoSuchElementException => println( "There is no such command" )
      case e: IllegalArgumentException => println( "It is expected the name of the file to be created" )
      case e: RuntimeException => println( "It is expected just the file/folder name after the command. Ex: mkdir MyPhotos" )

      unitializeCmdAndPaths()
    }
  }

  def makeFolder() : Unit = {
    currentLevel.whatContainsAtThisLevel = currentLevel.whatContainsAtThisLevel :+ new Folders( path1_from )

  }

  def listAllFilesAndFolders() : Unit = {
    currentLevel.whatContainsAtThisLevel.foreach(
      x => println( x.name )
    )
  }

  def unitializeCmdAndPaths() : Unit = {
    cmdPrefix = null
    path1_from = ""
    path2_to = ""
  }

}
