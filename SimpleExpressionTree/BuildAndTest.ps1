cls
Remove-Item bin\*;
javac -Xlint:unchecked `@argfile `@testArgfile;
java -cp bin`;lib\* MyTestRunner