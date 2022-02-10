package terminal

class Folders( val name : String) extends FileSystem {
  var whatContainsAtThisLevel : List[Folders] = List[Folders]()
  val typeOfThisObject = "F"

  var currentPath : String = ""
}
