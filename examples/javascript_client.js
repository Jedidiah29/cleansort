// JavaScript example using fetch
async function processMetadata(metadata) {
    try {
        const response = await fetch('http://localhost:5000/process', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ metadata }),
        });
        return await response.json();
    } catch (error) {
        console.error('Error:', error);
    }
}

async function retrieveMetadata(category = null) {
    try {
        const url = category 
            ? `http://localhost:5000/retrieve?category=${category}`
            : 'http://localhost:5000/retrieve';
        const response = await fetch(url);
        return await response.json();
    } catch (error) {
        console.error('Error:', error);
    }
}

// Example usage
const metadata = `
    <meta name="title" content="Test Book">
    <meta name="author" content="Test Author">
    <meta name="isbn" content="123-456-789">
    <meta name="source_site" content="example.com">
`;

// Process metadata
processMetadata(metadata)
    .then(result => console.log('Processed metadata:', result))
    .catch(error => console.error('Error:', error));
