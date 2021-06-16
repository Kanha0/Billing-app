# Billing-app
This app is a offline Billing app features of the app are as followed.
1. Create Quotation in pdf format.
2. Bill Invoice in pdf format.
3. Maintain Customer Details and save in the local database.
4. Store Bills and Quotations in the local database (Inside Room database using sqlite) .
5. Allow to share Bill and Quotation in different Platform.
6. It allow to save the pdf int External Storage into every android updates.  

In this the data is store into two data base  SQLite table inside Room data base, one for customer detailes and another for Bills and Quatations.
boths the tables are related throw an Id which help to sort the bills and Quotations for respected customer. 
 
 The pdf is created manullay inside a custom class.
 
 App allows to save the pdf into all latest updates of android into External Storage.
