import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Javame extends ApplicationAdapter {
    private SpriteBatch batch;
    private TextureRegion grass, dirt, stone;
    private Player player;
    
    // The World Data
    private int[][] map = new int[40][23];
    private final int TILE_SIZE = 32;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // 1. Generate textures
        grass = createSimpleTexture(0.2f, 0.8f, 0.2f);
        dirt  = createSimpleTexture(0.5f, 0.3f, 0.1f);
        stone = createSimpleTexture(0.5f, 0.5f, 0.5f);

        // 2. Setup Player (Needs a texture and a start position)
        TextureRegion playerTexture = createSimpleTexture(1, 0, 0); // Red
        player = new Player(playerTexture, 100, 500);

        // 3. Fill Map with dummy data
        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 23; y++) {
                if (y < 10) map[x][y] = 2;      // Dirt floor
                else if (y == 10) map[x][y] = 1; // Grass surface
                else map[x][y] = 0;             // Air
            }
        }
    }

    @Override
    public void render() {
        // --- 1. Update Logic ---
        float dt = Gdx.graphics.getDeltaTime();
        player.update(dt);

        // --- 2. Rendering ---
        Gdx.gl.glClearColor(0.5f, 0.8f, 1.0f, 1); // Sky Blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        
        // Draw the World
        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 23; y++) {
                int type = map[x][x]; // Wait! Note the fix below
                type = map[x][y]; 
                
                if (type == 1) batch.draw(grass, x * TILE_SIZE, y * TILE_SIZE);
                else if (type == 2) batch.draw(dirt, x * TILE_SIZE, y * TILE_SIZE);
                else if (type == 3) batch.draw(stone, x * TILE_SIZE, y * TILE_SIZE);
            }
        }

        // Draw the Player
        player.draw(batch);
        
        batch.end();
    }

    private TextureRegion createSimpleTexture(float r, float g, float b) {
        Pixmap p = new Pixmap(TILE_SIZE, TILE_SIZE, Pixmap.Format.RGBA8888);
        p.setColor(r, g, b, 1);
        p.fillRectangle(0, 0, TILE_SIZE, TILE_SIZE);
        p.setColor(0, 0, 0, 0.2f); 
        p.drawRectangle(0, 0, TILE_SIZE, TILE_SIZE);
        
        Texture t = new Texture(p);
        p.dispose();
        return new TextureRegion(t);
    }

    @Override
    public void dispose() {
        batch.dispose();
        // Tip: You should also dispose the textures inside your regions here!
    }
}
