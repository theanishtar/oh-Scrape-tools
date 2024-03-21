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

function htmlToMarkdown(html) {
      // Chuyển đổi HTML thành Markdown ở đây
      // Bạn có thể sử dụng một thư viện HTML-to-Markdown hoặc viết mã chuyển đổi tùy chỉnh
      // Đây là một ví dụ đơn giản:
      // (Lưu ý: Đây không phải là phương pháp hoàn chỉnh, mà chỉ là một ví dụ đơn giản)
      let markdown = html
        .replace(/<h1>/g, '# ')
        .replace(/<h2>/g, '## ')
        .replace(/<h3>/g, '### ')
        .replace(/<h4>/g, '#### ')
        .replace(/<h5>/g, '##### ')
        .replace(/<h6>/g, '###### ')
        .replace(/<\/h[1-6]>/g, '\n')
        .replace(/<p>/g, '')
        .replace(/<\/p>/g, '\n')
        .replace(/<br>/g, '\n')
        .replace(/<strong>/g, '**')
        .replace(/<\/strong>/g, '**')
        .replace(/<em>/g, '*')
        .replace(/<\/em>/g, '*')
        .replace(/<a[^>]* href="([^"]*)"[^>]*>(.*?)<\/a>/g, '[$2]($1)')
        .replace(/<\/?[^>]+(>|$)/g, '');

      return markdown;
    }

function generateMarkdown(data) {
  const markdown = htmlToMarkdown(data);
  return markdown;
}

function displayMarkdown(markdown) {
  const outputElement = document.getElementById('output');
  outputElement.textContent = markdown;
}
