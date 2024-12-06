using System;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

class CleansortClient
{
    private static readonly HttpClient client = new HttpClient();
    private const string API_URL = "http://localhost:5000";

    public async Task<string> ProcessMetadata(string metadata)
    {
        var payload = new { metadata = metadata };
        var content = new StringContent(
            JsonSerializer.Serialize(payload),
            Encoding.UTF8,
            "application/json"
        );

        var response = await client.PostAsync($"{API_URL}/process", content);
        response.EnsureSuccessStatusCode();
        return await response.Content.ReadAsStringAsync();
    }

    public async Task<string> RetrieveMetadata(string category = null)
    {
        string url = category != null 
            ? $"{API_URL}/retrieve?category={category}"
            : $"{API_URL}/retrieve";

        var response = await client.GetAsync(url);
        response.EnsureSuccessStatusCode();
        return await response.Content.ReadAsStringAsync();
    }

    static async Task Main()
    {
        try
        {
            var client = new CleansortClient();
            string metadata = @"
                <meta name=""title"" content=""Test Book"">
                <meta name=""author"" content=""Test Author"">
                <meta name=""isbn"" content=""123-456-789"">
                <meta name=""source_site"" content=""example.com"">
            ";

            // Process metadata
            string result = await client.ProcessMetadata(metadata);
            Console.WriteLine($"Processed metadata: {result}");

            // Retrieve all metadata
            string stored = await client.RetrieveMetadata();
            Console.WriteLine($"All stored metadata: {stored}");
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Error: {ex.Message}");
        }
    }
}
