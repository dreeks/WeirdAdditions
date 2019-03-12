package xyz.dreeks.weirdadditions.tileentities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.GL_LINES;

public class TESRPartyRender extends TileEntitySpecialRenderer<TEPartyRender> {

    public void render(TEPartyRender te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        double distance = Minecraft.getMinecraft().player.getPosition().distanceSq(x, y, z);

        double max = 20 - (distance * .01);

        initGL(max);
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(te.yaw * 360, 1f, 0f, 0f);
        drawCube(0, 0, 0, 1.f, 0f, 0f, 1f);
        closeGL();

        initGL(max);
        drawCube((float)x, (float)y+2, (float)z, 0.f, 1f, 0f, 1f);
        closeGL();

        initGL(max);
        drawCube((float)x+1, (float)y+1, (float)z, 0.f, 0f, 1f, 1f);
        closeGL();

        initGL(max);
        drawCube((float)x-1, (float)y+1, (float)z, 1.f, 1f, 0f, 1f);
        closeGL();
    }

    static void initGL(double d) {
        GlStateManager.pushMatrix();

        GlStateManager.glLineWidth((float) d);
        GlStateManager.disableTexture2D();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA);
    }

    static void closeGL() {
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    static void drawCube(float x, float y, float z, float r, float g, float b, float a) {
        GlStateManager.glBegin(GL_LINES);
        GlStateManager.color(r, g, b, a);

        GlStateManager.glVertex3f(x, y, z);
        GlStateManager.glVertex3f(x+1, y, z);
        GlStateManager.glVertex3f(x+1, y, z);
        GlStateManager.glVertex3f(x+1, y, z+1);
        GlStateManager.glVertex3f(x+1, y, z+1);
        GlStateManager.glVertex3f(x, y, z+1);
        GlStateManager.glVertex3f(x, y, z+1);
        GlStateManager.glVertex3f(x, y, z);

        GlStateManager.glVertex3f(x, y, z);
        GlStateManager.glVertex3f(x, y+1, z);

        GlStateManager.glVertex3f(x, y, z+1);
        GlStateManager.glVertex3f(x, y+1, z+1);

        GlStateManager.glVertex3f(x+1, y, z+1);
        GlStateManager.glVertex3f(x+1, y+1, z+1);

        GlStateManager.glVertex3f(x+1, y, z);
        GlStateManager.glVertex3f(x+1, y+1, z);

        GlStateManager.glVertex3f(x, y+1, z);
        GlStateManager.glVertex3f(x+1, y+1, z);
        GlStateManager.glVertex3f(x+1, y+1, z);
        GlStateManager.glVertex3f(x+1, y+1, z+1);
        GlStateManager.glVertex3f(x+1, y+1, z+1);
        GlStateManager.glVertex3f(x, y+1, z+1);
        GlStateManager.glVertex3f(x, y+1, z+1);
        GlStateManager.glVertex3f(x, y+1, z);

        GlStateManager.glEnd();
    }


    static void drawSquare(double x, double y, double z, float r, float g, float b, float a)
    {
        GlStateManager.glBegin(GL_LINES);

        GlStateManager.color(r, g, b, a);

        GlStateManager.glVertex3f((float)x, (float)y, (float)z);
        GlStateManager.glVertex3f((float)x+1, (float)y, (float)z);

        GlStateManager.glVertex3f((float)x+1, (float)y, (float)z);
        GlStateManager.glVertex3f((float)x+1, (float)y, (float)z+1);

        GlStateManager.glVertex3f((float)x+1, (float)y, (float)z+1);
        GlStateManager.glVertex3f((float)x, (float)y, (float)z+1);

        GlStateManager.glVertex3f((float)x, (float)y, (float)z+1);
        GlStateManager.glVertex3f((float)x, (float)y, (float)z);

        GlStateManager.glEnd();
    }
}
