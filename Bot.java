import java.net.*;
import java.net.http.*;

public class Bot {
    public static void main(String[] args) {
        String webhookUrl = System.getenv("SLACK_WEBHOOK_URL");

        HttpClient client = HttpClient.newHttpClient();

        // Block Kit을 이용한 Slack 메시지
        String payload = "{"
                + "\"blocks\": ["
                + "    {\"type\": \"section\", \"text\": {\"type\": \"mrkdwn\", \"text\": \"*🎉 유쾌한 봇 등장! 🎉*\"}},"
                + "    {\"type\": \"divider\"},"
                + "    {\"type\": \"section\", \"text\": {\"type\": \"mrkdwn\", \"text\": \"🚀 오늘도 힘내요! 봇이 특별한 응원을 보냅니다! 💪\"}},"
                + "    {\"type\": \"image\", \"image_url\": \"https://media.giphy.com/media/l0HlOvJ7yaacpuSas/giphy.gif\", \"alt_text\": \"funny gif\"}"
                + "]"
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(webhookUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString()
            );
            System.out.println("요청 코드: " + response.statusCode());
            System.out.println("응답 결과: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
