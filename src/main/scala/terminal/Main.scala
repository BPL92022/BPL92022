package terminal


object Main {
  def main(args: Array[String]): Unit = {

    val terminal = FileSystem

    val root: Folder = new Folder("/") // initial folder, the root as "/"

    val rootAddedFolder = terminal.mkdir(root.whatIsOnThisLevel, "Fotografii") //adding on the current level (root) a folder "Fotografii"

    terminal.ls(rootAddedFolder) // print: Fotografii(F)

    val rootAddedFile : List[Folder] = terminal.mkfile( rootAddedFolder, "poza Marea Neagra 2014.jpg" )

    terminal.ls( rootAddedFile )    // print: poza Marea Neagra 2014.jpg(f), Fotografii(F),

    val cdInTheFotografiiFolder : Option[Folder] = terminal.cd( rootAddedFile, "Fotografii" )

    if( cdInTheFotografiiFolder.isDefined ){ terminal.ls(cdInTheFotografiiFolder.get.whatIsOnThisLevel ) }      // nu afiseaza nimic pt ca whatIsOnThisLevel e gol din Fotografii

    //continua pt ca "cdInTheFotografiiFolder" nu e None
    val addFileInFotografii = terminal.mkfile( cdInTheFotografiiFolder.get.whatIsOnThisLevel, "fisier.txt" )

    terminal.ls( addFileInFotografii )      // afiseaza: fisier.txt(f),

    val fisierModificat = terminal.appendTextToFile( addFileInFotografii, "Acesta e continutul adaugat.", "fisier.txt" )


    println( terminal.cat( fisierModificat, "fisier.txt" ) )      //afiseaza: Acesta e continutul adaugat

    val fisierModificatDeDouaOri = terminal.appendTextToFile( fisierModificat,"Salve, acesta e un text adaugat a doua oara.", "fisier.txt"  )

    println( terminal.cat( fisierModificatDeDouaOri, "fisier.txt" ) )   //afiseaza: Acesta e continutul adaugatSalve, acesta e un text adaugat a doua oara.

    terminal.ls( fisierModificatDeDouaOri )       // fisier.txt(f),

    val amCreatUnFolder = terminal.mkdir( fisierModificatDeDouaOri, "ThisIsAFolder" )

    terminal.ls( amCreatUnFolder )          // ThisIsAFolder(F), fisier.txt(f),

    val amStersFisier = terminal.rm( amCreatUnFolder, "fisier.txt" )

    terminal.ls( amStersFisier )       //ThisIsAFolder(F),

    val maiCreeazaFisier = terminal.mkfile( amStersFisier, "pozaMe.jpg" )

    terminal.ls( maiCreeazaFisier )       // afiseaza: pozaMe.jpg(f), ThisIsAFolder(F),

    val copieFisier = terminal.cp( maiCreeazaFisier, "pozaMe.jpg", "pozaMe_backup.jpg" )

    terminal.ls( copieFisier )        // pozaMe_backup.jpg(f), pozaMe.jpg(f), ThisIsAFolder(F),

  }
}