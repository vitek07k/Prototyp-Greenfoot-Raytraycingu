# Úvod

Když jsem se poprvé seznámil s GreenFootem před asi čtyřmi měsíci na střední škole, nevěděl jsem o javě vůbec nic. Hned mě ale napadla otázka: Jak by toto jednoduché programovací prostředí vypadal, kdybych jej vytáhl k jeho absolutním limitům? :D

Takže tady jsem dnes, vytvářím svůj první GitHub repozitář abych mohl detailně zdokumentovat svoji práci.

Níže začnu s takzvanou "kapitoulou 1", je to první prototyp. Chtěl bych někdy pokračovat i s druhou kapitolou, kde bych přidal odlesky a stíny.

PS: Není to nejvíce uhlazený skript, ale prosím o toleranci, v javě jsem začal teprve před čtyřmi měsíci :D

# Kapitola 1

První prototyp RayTracingu, udělal jsem jej 8.12.2025 asi během 2 - 3 hodin.

Celá tato verze se nachází ve složce [/RayTracingV1](RayTracingV1/)
Skript, který zde budu dokumentovat je jen [MyWorld.java](RayTracingV1/MyWorld.java), protože jsem celý raytracer napsal jen do wordu, připadalo mi zbytečné dávat to do aktéra, protože celý engine lze jednoduše rozjet z jednoho .java souboru.

## Výpočet průsečíku paprsku
Paprsek je pomyslný bod, jehož poloha je zapsána třemi proměnamy (paprsekX, paprekY, paprsekZ)

Z platformy scratch.mit.edu (příště odkazuji jen jako scratch :D), jakožto můj první programovací jazyk jsem byl zvyklí na souřadnicoví systém, kde x0 a y0 byly uprostřed. Greenfoot však má výchozí souřadnici v levém horním rohu, což je pro mě trochu nešikovné. Z tohoto důvodu jsem si systém převedl na takový, jaký má scratch

[Výpočet průsečíku paprsku](RayTracingV1/MyWorld.java#L106-L109)
PS: paprsekX,Y a Z jsou na začátku světa definované jako privátní double proměnné. DrawX,Y jsou private int. A kameraX,Y a Z také private int.
