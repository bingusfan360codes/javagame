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
    private int[][] map = new int[40][23];
    private final int TILE_SIZE = 32;

    @Override
    public void create() {
        batch = new SpriteBatch();


        grass = createSimpleTexture(0.2f, 0.8f, 0.2f); // Green
        dirt  = createSimpleTexture(0.5f, 0.3f, 0.1f); // Brown
        stone = createSimpleTexture(0.5f, 0.5f, 0.5f); // Gray

        // Fill the world array
        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 23; y++) {
                if (y < 8) map[x][y] = 3;          // Stone
                else if (y < 12) map[x][y] = 2;    // Dirt
                else if (y == 12) map[x][y] = 1;   // Grass
                else map[x][y] = 0;                // Sky
            }
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
