const express = require('express');
const axios = require('axios');

const app = express();
const port = 3000;

// API endpoint
app.get('/api/data', async (req, res) => {
  try {
    // Gửi yêu cầu GET đến URL bên ngoài
    const response = await axios.get('https://caodang.fpt.edu.vn/tin-tuc-poly/can-tho/nam-sinh-fpt-polytechnic-can-tho-tai-nang-sang-tao-he-thong-hoc-lap-trinh-bo-ich.html');

    const mdRes = htmlToMarkdown(response.data);
    // Trả về dữ liệu nhận được từ URL bên ngoài
    res.json(mdRes);
  } catch (error) {
    // Xử lý lỗi nếu có
    console.error('Error fetching data:', error);
    res.status(500).json({ error: 'Could not fetch data' });
  }
});

// Khởi động server
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});

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