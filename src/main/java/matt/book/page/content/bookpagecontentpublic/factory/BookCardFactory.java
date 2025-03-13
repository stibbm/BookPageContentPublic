package matt.book.page.content.bookpagecontentpublic.factory;

import matt.book.page.content.bookpagecontentpublic.model.BookCard;

public class BookCardFactory {
    public BookCard buildBookCard(Book book, List<Chapter> chapterList) {
        List<Chapter> lastTwoChapterList = new ArrayList<>();
        if (chapterList.size() > 0) {
            if (chapterList.get(0).getChapterId().getChapterNumber().longValue()
                    <= chapterList.get(chapterList.size()-1).getChapterId().getChapterNumber().longValue()) {
                lastTwoChapterList.add(chapterList.get(chapterList.size() - 1));
            }
            else {
                lastTwoChapterList.add(chapterList.get(0));
            }
        }
        if (chapterList.size() > 1) {
            if (chapterList.get(0).getChapterId().getChapterNumber().longValue()
                <= chapterList.get(chapterList.size()-1).getChapterId().getChapterNumber().longValue()) {
                lastTwoChapterList.add(chapterList.get(chapterList.size()-2));
            }
            else {
                lastTwoChapterList.add(chapterList.get(1));
            }
        }
        BookCard bookCard = BookCard.builder()
                .bookNumber(book.getBookNumber())
                .bookName(book.getBookName())
                .createdBy(book.getCreatedBy())
                .bookViews(book.getBookViews())
                .lastTwoChapters(lastTwoChapterList)
                .thumbnailRelativeImageUrl(book.getBookThumbnail())
                .build();
        return bookCard;
    }
}
