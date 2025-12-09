# Úvod

Když jsem se poprvé seznámil s GreenFootem před asi čtyřmi měsíci na střední škole, nevěděl jsem o javě vůbec nic. Hned mě ale napadla otázka: Jak by toto jednoduché programovací prostředí vypadal, kdybych jej vytáhl k jeho absolutním limitům? :D

Takže tady jsem dnes, vytvářím svůj první GitHub repozitář abych mohl detailně zdokumentovat svoji práci.

Níže začnu s takzvanou "kapitoulou 1", je to první prototyp. Chtěl bych někdy pokračovat i s druhou kapitolou, kde bych přidal odlesky a stíny.

PS: Není to nejvíce uhlazený skript, ale prosím o toleranci, v javě jsem začal teprve před čtyřmi měsíci :D

# Kapitola 1

První prototyp RayTracingu, udělal jsem jej 8.12.2025 během cca. 2 - 3 hodin.

Celá tato verze se nachází ve složce [/RayTracingV1](RayTracingV1/).
Skript, který zde budu dokumentovat je pouze [MyWorld.java](RayTracingV1/MyWorld.java), protože jsem celý raytracer napsal jen do wordu - připadalo mi zbytečné dávat to do aktéra, protože celý engine lze jednoduše rozjet z jedniné třídy.

## Výpočet průsečíku paprsku
Paprsek je pomyslný bod v 3D prostoru s vektorem, jehož poloha je zapsána třemi proměnamy (paprsekX, paprekY, paprsekZ)
Průsečík paprsku je bod, zkrz který bude raytracer posílat paprsek, nachází se v 3D prostoru kus před kamerou a mění se s pozicí pixelu kterého právě chceme rendrovat v 2D scéně

Z platformy scratch.mit.edu (příště odkazuji jen jako scratch :D), jakožto můj první programovací jazyk, jsem byl zvyklí na souřadnicoví systém, kde x0 a y0 byly uprostřed. Greenfoot však má výchozí souřadnici v levém horním rohu, což je pro mě trochu nešikovné. Z tohoto důvodu jsem si v následujícím kódu systém převedl na takový, jaký má scratch.

```java
//Vypočítání průsečíku paprsku
pixelX = (drawX - getWidth()/2) + KameraX;
pixelY = -(drawY - getHeight()/2) + KameraY;
pixelZ = vzdalenostKObrazovce + KameraZ;
```
[> Odkaz na kód <](RayTracingV1/MyWorld.java#L106-L109)

PixelX,Y a Z tady odkazuje na bod kterým skript bude posílat paprsek (průsečík paprsku), odečetl jsem od X a Y polovinu rozměru obrazovky tak, aby byl základní bod uprostřed obrazovky. To napodobuje to souřadnicoví systém jako má scratch.
DrawX a Y odkazují na pixelu na 2D scéně, na který budeme kreslit.
'vzdalenostKObrazovce' je zde proměnná, která funguje jako opačné FOV, čím je větší, tím užšším "tunelem" se budeme dívat.
KameraXYZ je pozice kamery, lze ji na začátku skriptu upravit a pohybovat se tímpádem v prostoru.

PS: paprsekXYZ jsou na začátku světa definované jako privátní double proměnné. KameraXYZ a DrawXYZ jsou private int. 
Otáčení kamery v této verzi ještě není.

## Přípravy na započatí sledování paprsku

```java
//Započatí sledování paprsku
paprsekX = KameraX;
paprsekY = KameraY;
paprsekZ = KameraZ;
        
krok = 0;
sledovatPaprsek = true;
```
[> Odkaz na kód <](RayTracingV1/MyWorld.java#L114-L120)

PaprsekXYZ je pozicí paprsku kterého budeme později při sledování "Tracingu" posouvat vektorem, který směřuje zkrz pixelXYZ (průsečík paprsku). Jeho pozice se zde na začátek nastavuje na pozici kamery - z té bude startovat.
Proměnná 'krok' (int, privátní) je počítadlo, které se bude zvyšovat pokaždé když paprsek posunem vektorem - to je velice důležité - po určitých krocích je třeba paprsek zastavit kdyby do ničeho nenarazil a zamezit tak vzniku nekonečné smyčky.






        
