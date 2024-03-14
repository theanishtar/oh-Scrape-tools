async function scrapeData() {
  const url = document.getElementById('urlInput').value;
  try {
    const response = await fetch(url);
    const html = await response.text();
    const data = parseHTML(html);
    const markdown = generateMarkdown(data);
    displayMarkdown(markdown);
  } catch (error) {
    console.error('Error scraping data:', error);
    displayMarkdown('Error scraping data. Please check the URL and try again.');
  }
}

function parseHTML(html) {
  // Viết mã để phân tích HTML và trích xuất dữ liệu cần thiết ở đây
  // Đây là nơi bạn sẽ sử dụng các công cụ như DOMParser hoặc thư viện bên thứ ba như Cheerio
  // Ví dụ:
  const data = {
    title: 'Sample Title',
    content: 'Sample Content',
  };
  return data;
}

function generateMarkdown(data) {
  const markdown = `
# ${data.title}

${data.content}
  `;
  return markdown;
}

function displayMarkdown(markdown) {
  const outputElement = document.getElementById('output');
  outputElement.textContent = markdown;
}
