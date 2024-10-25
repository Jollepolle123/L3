# L3

I denna java applikationen så kan du redigera dina bilder, få en förhandsgranskning på hur resultatet blir och sedan spara ner bilden där du får välja typ och namn på filen och sedan vart på din dator den ska sparas.

## Starta applikationen
För att starta applikationen så laddar man ner projektet och går in i projektets mapp "L3" och kör gradle run. Om du inte vill använda dig av gradle så kan man även köra main.java filen med Microsofts "extension pack for java" i VSC. Detta kräver att du har java på din dator.

## Tester

| Testfall                                                | Indata                                                                                      | Förväntning |
| --------------------------------------------------------|:-------------------------------------------------------------------------------------------:|:-----------:|
| Ladda upp en bild.                                      | Starta applikationen. Klicka på "upload file", välj en png eller jpeg och klicka på "open". | Nu ska du kunna se din bild och knappar/textfält för att redigera bilden i applikationen. |
| Spara ner bilden redigerade bilden.                     | Ladda upp en bild i aplikationen.  Skriv in namn och välj filtyp på bilden som ska sparas. Klicka på "save image" och välj vart den ska sparas. Klicka på "open" | Din bild ska nu vara sparad i den mappen du valt med namnet du valde och filtypen. |
| Ändra storlek på en bild.	                              | Ladda upp en bild i aplikationen. Skriv in önskad bredd och höjd på bilden i textfälten "width" och "height". Klicka på "resize image". | Din bild ska uppdateras i applikationen till en bild som har samma storlek som valts. |
| Rotera en bild.                                         | Ladda upp en bild i aplikationen. Skriv in önskad rotation på bilden i textfältet "rotation". Klicka på "rotate image". | Bilden i applikationen skall uppdateras och roteras så mycket som du valt. |
| Ändra ljusstyrka på en bild	                          | Ladda upp en bild i aplikationen. Skriv in 1.5 i textfältet "brightness multiplier". Klicka på "adjust brightness". | Bilden i applikationen ska nu vara uppdaterad och ha 50% starkare ljusstyrka.      |
| Ändra röda, gröna och blåa färgerna på en bild.         | Ladda upp en bild i aplikationen. Skriv in 0 i textfälten "red" och "green", skriv 1 i textfältet "blue". Klicka på "change colors". | Bilden ska nu vara uppdaterad och kommer se blå ut. |
| Invertera färgerna på en bild.	                      | Ladda upp en bild i aplikationen. Klicka på "invert colors" | Bilden kommer nu vara uppdaterad och färgerna på bilden är motsatta som original bilden. |


OBS! Applikationen renderar bilderna i sin riktiga storlek, så har du en bild som är större än fönstret kommer inte hela bilden synas i applikationen. Vissa png bilder fungerar ibland inte att spara ner som jpeg.