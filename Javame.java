import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Javame extends ApplicationAdapter {
    private Player player;
    // ... your other variables ...

    @Override
    public void create() {
        // ... (your texture generation code) ...
        
        TextureRegion playerTexture = createSimpleTexture(1, 0, 0); // Red player
        player = new Player(playerTexture, 100, 500); // Start in the sky
    }

    @Override
    public void render() {
        // 1. Update Logic
        float dt = Gdx.graphics.getDeltaTime();
        player.update(dt);

        // 2. Draw Logic
        Gdx.gl.glClearColor(0.5f, 0.8f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        renderWorld(); // Your loop that draws the map array
        player.draw(batch);
        batch.end();
    }
}
    // Creates tile
    private TextureRegion createSimpleTexture(float r, float g, float b) {
        Pixmap p = new Pixmap(TILE_SIZE, TILE_SIZE, Pixmap.Format.RGBA8888);
        p.setColor(r, g, b, 1);
        p.fillRectangle(0, 0, TILE_SIZE, TILE_SIZE);
        
        // Add a small border
        p.setColor(0, 0, 0, 0.2f); 
        p.drawRectangle(0, 0, TILE_SIZE, TILE_SIZE);
        
        Texture t = new Texture(p);
        p.dispose();
        return new TextureRegion(t);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.5f, 0.8f, 1.0f, 1); // Sky Blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 23; y++) {
                int type = map[x][y];
                if (type == 1) batch.draw(grass, x * TILE_SIZE, y * TILE_SIZE);
                else if (type == 2) batch.draw(dirt, x * TILE_SIZE, y * TILE_SIZE);
                else if (type == 3) batch.draw(stone, x * TILE_SIZE, y * TILE_SIZE);
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
