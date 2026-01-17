# Book Page Content (public)

## System Architecture

<img width="7840" height="3160" alt="ArchitectureDiagram" src="https://github.com/user-attachments/assets/7c54298a-6cd0-4cfb-acc4-667efa35df81" />

## Entity-Relationship Diagram

<img width="2352" height="1896" alt="ERDiagram" src="https://github.com/user-attachments/assets/96cbe857-91d7-4841-bba7-0c9318674b22" />


## Book Endpoints

**BookController**

* GET "/getAllBooksPaged"
* GET "/getAllBookCardsPaged"
* GET "/getAllBookCardsSortedPaged"
* POST "/createBook"

**BookDetailsController**

* GET "/getBookDetailsCardByBookName"
* GET "/getBookDetailsCardByBookNameBatched"

**PublicBookDetailsCardController**

* GET "/getPublicBookDetailsCardByBookNumber"

**BookViewController**

* GET "/createBookView"


## Chapter Endpoints

**ChapterController**

* POST "/createChapterWithSpecifiedChapterNumber"
* POST "/createChapter"

**ChapterHeaderController**

* GET "/getChapterHeadersByBookName"


## Image Endpoints

**ImageController**

* GET "/getImagesByBookNameAndChapterNumberPaged"
* POST "/createImage"
* GET "/getImage"


## Milestone Endpoints

**Milestone Controller**

* GET "/updateMilestone"
* GET "/getMilestone"


## Search Endpoints

**SearchController**

* GET "/searchBooksByBookTags"
* GET "/searchBooksByContent"
* GET "/searchBookCardsByBookTags"
* GET "/searchBookCardsByContent"

## Account Endpoints

**AccountController**

* GET "/createAccount"
* GET "/getAccount"

## Video Endpoints

**VideoController**

* GET "/createVideo"
* GET "/getVideosByBookNameAndChapterNumber"
* GET "/getVideo"




