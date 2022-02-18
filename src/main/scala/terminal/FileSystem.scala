package terminal

object FileSystem {

  def ls(currentFolderList: List[Folder]): Unit = {
    for (content <- currentFolderList) {
      print(s"${content.name}(${content.typeOfThis}), ")
    }
    println()
  }

  def mkdir(currentFolderList: List[Folder], folderName: String): List[Folder] = {
    new Folder(folderName) :: currentFolderList
  }

  def mkfile(currentFolderList: List[Folder], fileName: String, contentFile : String = ""): List[Folder] = {
    new File(fileName, contentFile) :: currentFolderList
  }

  def cd(currentFolderList: List[Folder], folderName: String): Option[Folder] = {
    for (folder <- currentFolderList) {
      if (folder.name == folderName)
        return Some(folder)
    }
    None
  }

  def appendTextToFile(currentFolderList: List[Folder], contentToBeAdded: String, fileName: String): List[Folder] = {

    for (item <- currentFolderList) {

      if (item.name == fileName) {
        val obiectDeModificat: File = item.asInstanceOf[terminal.File] //trateaza itemul ca File si nu ca Folder; File e child class din Folder
        val continutNou: String = obiectDeModificat.contentFile + contentToBeAdded
        val listaFaraFisierulVechi : List[Folder] = rm( currentFolderList, fileName )
        return mkfile( listaFaraFisierulVechi, fileName, continutNou )
      }
    }
    currentFolderList
  }

  def cat( currentFolderList: List[Folder], fileName: String ) : String = {
    for (item <- currentFolderList) {

      if (item.name == fileName) {
        val fisierulDeVizualizat: File = item.asInstanceOf[terminal.File] //trateaza itemul ca File si nu ca Folder; File e child class din Folder
        return fisierulDeVizualizat.contentFile
      }

    }
    s"Nu exista fisierul $fileName"
  }

  def rm( currentFolderList: List[Folder], fileName: String ) : List[Folder] = {
    for( item <- currentFolderList ){
      if( item.name == fileName ){
        return currentFolderList.filter( _ != item )
      }
    }
    currentFolderList
  }

  def cp( currentFolderList: List[Folder], fileName: String, fileName2 : String ) : List[Folder] = {
    for( item <- currentFolderList ){
      if(item.name == fileName){
        val obiectDeModificat: File = item.asInstanceOf[terminal.File]
        return mkfile( currentFolderList, fileName2, obiectDeModificat.contentFile )
      }
    }
    println(s"THere is no such file $fileName")
    currentFolderList
  }
}
