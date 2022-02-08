

class FileSystem {
  def commandInterpretor() : Unit = {
    print( "Your cmd (press \"q\" to quit, \"h\" for help): " )
    val inputKeyboard = getKeyboardInput

    val cmdMatcher = inputKeyboard match{
      case "q" => "The key q is pressed, the app will close."
      case "h" =>  help(); commandInterpretor()
      case "curdir" => println( getCurrentPath ); commandInterpretor()
      case _   => println("""The command is not recognized. Type "h" to list the commands and how to use them or "q" to quit."""); commandInterpretor()
    }

    println( cmdMatcher )
  }

  def help() : Unit = {
    println(
      "Run the following commands (without quotes):\n" +
        "\"curdir\" = will show the current directory"
    )
  }


  def getKeyboardInput : String = scala.io.StdIn.readLine()

  def getCurrentPath : String = System.getProperty("user.dir")

  def filesAndFoldersInGivenPath( path : String ) : List[String] = {
    new java.io.File(path).list().toList
  }

}
