from cleansort import CleanSort

def main():
    # Initialize the library
    cleansort = CleanSort()
    
    # Example 1: HTML metadata
    html_metadata = """
    <meta name="title" content="The Great Book">
    <meta name="author" content="Jane Smith">
    <meta name="isbn" content="978-3-16-148410-0">
    <meta name="source_site" content="library.com">
    <meta name="irrelevant" content="this will be removed">
    """
    
    print("Processing HTML metadata:")
    result = cleansort.process_metadata(html_metadata)
    print("Cleaned and sorted metadata:", result)
    print()
    
    # Example 2: Text metadata
    text_metadata = """
    title: Python Programming
    author: John Doe
    isbn: 123-456-789
    source_site: programming.com
    irrelevant: this will be removed
    """
    
    print("Processing text metadata:")
    result = cleansort.process_metadata(text_metadata)
    print("Cleaned and sorted metadata:", result)
    print()
    
    # Retrieve all stored metadata
    print("All stored metadata:")
    stored_data = cleansort.get_stored_metadata()
    print(stored_data)
    
    # Retrieve only authors
    print("\nAuthors only:")
    authors_data = cleansort.get_stored_metadata('authors')
    print(authors_data)

if __name__ == "__main__":
    main()
