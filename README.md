# Book Page Content (public)


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




