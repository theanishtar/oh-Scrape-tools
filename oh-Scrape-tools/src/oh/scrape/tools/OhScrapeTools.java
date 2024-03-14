/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oh.scrape.tools;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author ADMIN
 */
public class OhScrapeTools  extends JFrame {

    private JTextField urlField;
    private JButton scrapeButton;
    private JTextArea markdownArea;

    public OhScrapeTools() {
        setTitle("Web Scraper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        urlField = new JTextField();
        scrapeButton = new JButton("Scrape");
        markdownArea = new JTextArea();

        JScrollPane scrollPane = new JScrollPane(markdownArea);

        setLayout(new BorderLayout());
        add(urlField, BorderLayout.NORTH);
        add(scrapeButton, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        scrapeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                String markdown = scrapeAndConvertToMarkdown(url);
                markdownArea.setText(markdown);
            }
        });
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
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error scraping data. Please check the URL and try again.";
        }

        return markdownBuilder.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OhScrapeTools().setVisible(true);
            }
        });
    }
    
}
