import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Jaylib.VIOLET;
import static com.raylib.Raylib.*;

public class Main {
    public static void main(String[] args) {
        InitWindow(1280, 720, "Sidescroller");
        SetTargetFPS(60);
        while (!WindowShouldClose()) {
            BeginDrawing();
            ClearBackground(RAYWHITE);

            EndDrawing();
        }
        CloseWindow();
    }
}