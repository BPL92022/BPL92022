package terminal

class File(name: String,val contentFile : String = "") extends Folder(name) {
  override val typeOfThis = "f"
}
