package terminal

class Folder(val name: String)  {
  val typeOfThis = "F"
  val whatIsOnThisLevel : List[Folder] = List[Folder]()
}
