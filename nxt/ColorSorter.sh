JAVA_HOME="/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home"

cd src/main/java

find . -name '*.class' -type f -delete
find . -name '*.nxj' -type f -delete

/Applications/leJOS_NXJ/bin/nxjupload ../../../../resources/sounds/Red.wav
/Applications/leJOS_NXJ/bin/nxjupload ../../../../resources/sounds/Green.wav
/Applications/leJOS_NXJ/bin/nxjupload ../../../../resources/sounds/Blue.wav
/Applications/leJOS_NXJ/bin/nxjupload ../../../../resources/sounds/Yellow.wav
/Applications/leJOS_NXJ/bin/nxjupload ../../../../resources/sounds/Error.wav

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/colorsorter/Dispenser.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/Dispenser.nxj legomindstorms.nxt.colorsorter.Dispenser
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/Dispenser.nxj

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/colorsorter/ColorDetector.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/ColorDetector.nxj legomindstorms.nxt.colorsorter.ColorDetector
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/ColorDetector.nxj

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/colorsorter/SortingTray.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/SortingTray.nxj legomindstorms.nxt.colorsorter.SortingTray
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/SortingTray.nxj

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/colorsorter/ColorCatapult.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/ColorCatapult.nxj legomindstorms.nxt.colorsorter.ColorCatapult
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/ColorCatapult.nxj

find . -name '*.class' -type f -delete
find . -name '*.nxj' -type f -delete