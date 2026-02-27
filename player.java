import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Vector2 velocity;
    private TextureRegion texture;
    private final float SPEED = 200f;
    private final float GRAVITY = -10f;

    public Player(TextureRegion texture, float x, float y) {
        this.texture = texture;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
    }

    public void update(float deltaTime) {
        // 1. Basic Movement
        if (Gdx.input.isKeyPressed(Keys.A)) velocity.x = -SPEED;
        else if (Gdx.input.isKeyPressed(Keys.D)) velocity.x = SPEED;
        else velocity.x = 0;

        // 2. Simple Gravity (we will add floor collision later)
        velocity.y += GRAVITY;

        // 3. Apply velocity to position
        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
    }

    public void draw(SpriteBatch batch) {
        // Draw the player (usually taller than a block)
        batch.draw(texture, position.x, position.y, 24, 48);
    }
}
