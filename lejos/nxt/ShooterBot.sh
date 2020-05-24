JAVA_HOME="/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home"

cd src/main/java

find . -name '*.class' -type f -delete
find . -name '*.nxj' -type f -delete

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/shooterbot/DrivingBase.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/DrivingBase.nxj legomindstorms.nxt.shooterbot.DrivingBase
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/DrivingBase.nxj

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/shooterbot/ColorDetection.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/ColorDetection.nxj legomindstorms.nxt.shooterbot.ColorDetection
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/ColorDetection.nxj

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/shooterbot/Shooter.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/Shooter.nxj legomindstorms.nxt.shooterbot.Shooter
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/Shooter.nxj

/Applications/leJOS_NXJ/bin/nxjc legomindstorms/nxt/Base.java legomindstorms/nxt/behaviors/*.java legomindstorms/nxt/shooterbot/LocateObjects.java
/Applications/leJOS_NXJ/bin/nxjlink -o legomindstorms/nxt/LocateObjects.nxj legomindstorms.nxt.shooterbot.LocateObjects
/Applications/leJOS_NXJ/bin/nxjupload legomindstorms/nxt/LocateObjects.nxj

find . -name '*.class' -type f -delete
find . -name '*.nxj' -type f -delete