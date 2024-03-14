/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.scrape.tools;

/**
 *
 * @author ADMIN
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class WebScraperApp extends JFrame {
    private JTextField urlField;
    private JButton scrapeButton;
    private JTextArea markdownArea;

    public WebScraperApp() {
        setTitle("Web Scraper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        urlField = new JTextField();
        scrapeButton = new JButton("Scrape");
        markdownArea = new JTextArea();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("URL: "), BorderLayout.WEST);
        topPanel.add(urlField, BorderLayout.CENTER);
        topPanel.add(scrapeButton, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(markdownArea);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        scrapeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                String markdown = scrapeAndConvertToMarkdown(url);
                markdownArea.setText(markdown);

                saveMarkdownToFile(markdown); // Gọi hàm để lưu Markdown vào tệp
            }
        });
    }

    public static String convert(String html) {
        // Thực hiện các phần tương ứng để chuyển đổi HTML thành Markdown
        // Trong ví dụ này, chúng ta chỉ đơn giản làm thay đổi một số thẻ HTML thành cú pháp Markdown
        String markdown = html;

        // Ví dụ: Chuyển đổi thẻ <h1> thành cú pháp Markdown ##
        markdown = markdown.replaceAll("<h1[^>]*>(.*?)</h1>", "## $1\n");

        // Ví dụ: Chuyển đổi thẻ <h2> thành cú pháp Markdown ##
        markdown = markdown.replaceAll("<h2[^>]*>(.*?)</h2>", "### $1\n");

        // Ví dụ: Chuyển đổi thẻ <p> thành cú pháp Markdown ##
        markdown = markdown.replaceAll("<p[^>]*>(.*?)</p>", "$1\n");

        // Và cách thức chuyển đổi các thẻ khác có thể được thêm vào tùy theo nhu cầu của bạn

        return markdown;
    }

    
    private String scrapeAndConvertToMarkdown(String url) {
        StringBuilder markdownBuilder = new StringBuilder();

        try {
            URLConnection connection = new URL(url).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                // Điều chỉnh mã để phân tích nội dung từ URL và chuyển đổi sang Markdown
                // Ví dụ: markdownBuilder.append("### ").append(line).append("\n");
                markdownBuilder.append(line).append("\n");
                System.out.println();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error scraping data. Please check the URL and try again.";
        }

        return markdownBuilder.toString();
    }

    private void saveMarkdownToFile(String markdown) {
        markdown = convert(markdown);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Markdown File");
        
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(fileToSave)) {
                writer.println(markdown);
                JOptionPane.showMessageDialog(this, "Markdown file saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving Markdown file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WebScraperApp().setVisible(true);
            }
        });
    }
}

