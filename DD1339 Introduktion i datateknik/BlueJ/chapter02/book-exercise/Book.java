/**
 * A class that maintains information on a book.
 * This might form part of a larger application such
 * as a library system, for instance.
 *
 * @author (Felix De Silva)
 * @version (02-10-13)
 */
class Book
{
    // The number og pages in the book
    private int pages;
    // The author of the book
    private String author;
    // The title of the book
    private String title;
    // The refrence number for the book
    private String refNumber;
    // The number of time the book has been borrowed
    private int borrowed;

    /**
     * Set the author and title fields when this object
     * is constructed.
     */
    public Book(String bookAuthor, String bookTitle, int numberOfPages){
        author = bookAuthor;
        title = bookTitle;
        pages = numberOfPages;
        refNumber = "";
    }

    /**
     * Returns the author of the book
     */
    public String getAuthor(){
        return author;
    }

    /**
     * Returns the title of the book
     */
    public String getTitle(){
        return title;
    }

    /**
     * Returns the number of pages in the book
     */
    public int getPages(){
        return pages;
    }

    /**
     * Returns the reference number for this book
     */
    public String getRefNumber(){
        return refNumber;
    }
    
    /**
     * Returns the borrow-count for this book
     */
    public int getBorrowed(){
        return borrowed;
    }

    /**
     * Sets the reference number for this book, if it's longer then 3 characters
     */
    public void setRefNumber(String ref){
        //checks it the ref.length() is bigger then 3 characters
        if(ref.length()<3){
            System.out.println("Refrence Number to short");
        }else{
            refNumber = ref;
        }
    }

    /**
     * Borrow the book!, increases its borrow-count by 1
     */
    public void Borrow(){
        borrowed += 1;
    }
    
    /**
     * Prints the title of the book
     */
    public void showTitle(){
        System.out.println(title);
    }

    /**
     * Prints the author of the book
     */
    public void showAuthor(){
        System.out.println(author);
    }

    /**
     * Prints the number of pages in the book
     */
    public void showPages(){
        System.out.println(pages);
    }

    /**
     * Prints the details of the book
     */
    public void showDetails(){
        System.out.println("Book info:");
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Pages: " + pages);
        
        //checks if the refNumber.length() is bigger then 0
        if(refNumber.length() > 0){
            System.out.println("Refrence number: " + refNumber);
        }else{
            System.out.println("Refrence number: ZZZ");
        }
        
        System.out.println("Number of times borrowed: " + borrowed);
    }
}
