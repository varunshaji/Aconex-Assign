Aconex-Assign
=============

Aconex-Assignment 

HOWTO - PhoneEncoder-1.0

The application comes as a executable jar - PhoneEncoder-1.0.jar .
The environment requirements are:

  •	Java SE 1.7.0_21

To execute the application use the command

  •	java -jar PhoneEncoder-1.0.jar  <Phonebook file> -d <Dictionary file>
  
  •	java -jar PhoneEncoder-1.0.jar  
   o	Takes the default dictionary and  depend on user based input from console for phone numbers
   
  •	java -jar PhoneEncoder-1.0.jar  -d <Dictionary file>
   o	Takes the configured dictionary file and encodes the text entered through console using it
   
  •	java -jar PhoneEncoder-1.0.jar  <Phonebook file>
   o	Make used of default dictionary for encoding the phone numbers mentioned in Phonebook file.

The default dictionary (“defaultDict.dict”)is enclosed in the distribution zip, other custom dictionary could be built adhering to the formats used. And could be used in the program by mentioning as –d <Dictionary file>. See to it that the program and the files reside in the same directory, else path need to be mentioned.
The default format for the phonebook file (“PhoneBook.phn”) which contains phone numbers to be processed is also enclosed within the distribution zip.

For further knowledge on various aspect please refer the javadoc or mail me at shajivarun@yahoo.co.in

Thank you.
