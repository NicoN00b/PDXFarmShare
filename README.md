# PDX Farm Share

#### PDX Farm is Application designed to open up the potential of the gift and barter economy in relation to our wild and cultivated abundance in the Portland Metro - 8.25.17

#### By Nicholas Raethke

## Description

This is the beginning of a scalable project that connects users for the purpose of distributing food, medicine, and related items. Here is the Current Data Schema:

![Database schema](/src/main/resources/public/images/PDXFarmSchema.JPG "Schema")


This product intends to connect urban farmers, gleaners, food banks, foodies without ample resources, and anyone else interested in locally raised food.  It will implement mapping data to connect people to the most convenient resources and incorporate existing resources into the data, specifically fallingfruit.org, which has an open source map of fruit trees and other free food.

## Setup/Installation Requirements

At this point use will require an IDE and a program such as POSTMAN to see the Data Model.  The app currently uses Java with PostgreSQL tables.  You can expect a full front-end incorporating Javascript and Android in the future.


## Known Bugs

This project is not out of the gradle yet (punny? yes!), it needs more routing and testing of it's one to many inheritance (Item as a super class over subclasses Fruit, Veggie, Herb, and Other), as well as, further development (I would still like to implement a single table inheritance, but documentation for it in Java is scant).  Here you can see the progress thanks to the snipping tool...

![Post](/src/main/resources/public/images/PostItemNestedInUser.JPG "Posting with the POSTMAN")

![Get All Items](/src/main/resources/public/images/GETItems.JPG "Getting them all")

![Get Item by Id](/src/main/resources/public/images/GETItemNestedInUser.JPG "By the Numbers")

(please see further images in /public/images)

## Support and contact details

please share your suggestions with me NicoN00b @ nicholas.raethke@gmail.com

## Technologies Used

This project is written in Java using IntelliJ IDE.  There are also so many dependencies I must thank.  Gradle, Maven, Spark, you know I couldn't do this without you.  POSTMAN, in a world with no front-end, you are the last Kevin Costner left to save us.
Atom, I love writing my README.md files inside your borders.  Github and git bash, I would be nowhere without you.

### License

use it, but don't charge people to use it!!! Creative Commons and Open Source 2017 NicoN00b.
