package terminal

class Folders( val name : String) extends FileSystem {
  var whatContainsAtThisLevel : List[Folders] = List[Folders]()

  var currentPath : String = ""
}
