This repository contains the code of UCL COMP0022 (2022) MovieDB project for Team 2.


2.9 commit details
1. updated .gitigore to stop committing files in the target folder
2. deleted jar file in the lib folder 
3. added spring configurations
4. added relevant services and controllers
5. added relevant dependencies in the pom file
6. added auto injection in relevant classes
7. added front end files and achieved examples on use case 1 and use case 2

**Instructions:**
After starting the server,<br>
To visit example page, goto: <br>
http://localhost:8080/MovieDB_war_exploded/example.html<br>
To visit homepage, where all further works shall be carried, goto:<br>
http://localhost:8080/MovieDB_war_exploded/homepage.html 


**Instructions for front end developer:**
To request certain information, change the ajax parameters.
Check the examples given and the instructions below:

In the data sent to back end, please always provide two String lists, the first one of size 4, the second one of size 2.<br>
Please fill "" (empty string) if the user has not specified that parameter.   
                                          
@param selectParams [titleParam, ratingsParam, genreParam, yearParam] 
                                                 
                    titleParam:      the input string in the searchBar by the user                              
                    ratingParam:     the ratings string.                                                                
                                     For example, "-4" represents user wants rating from 0 to 4                             
                                     "1-3" represents user wants rating from 1 to 3
                    genreParam:      the genre string selected by the user
                                     see database for all genres
                    yearParam:       the year string selected by the user
                    
                    
@param sortParams [sortParam, order, limit]

                     sortParam:      the sort method required by the user
                                     "title" for order by title
                                     "rating" for order by rating
                                     "year" for order by time
                     order:          "asc" for ascending order
                                     "desc" for descending order
                     limit:          "[startIndex],[range]"
                                     For example
                                     "0,10": read items 1-10
                                     "10,10": read items 11 - 20
                                     "10,30": read items 11 - 40

