package com.itver.solarsystem.models;



import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;

public class Sun {
	private GL2 gl;
	private GLU glu;
	private Texture sunTexture;
	private float angle = 0;

	public Sun(GL2 gl, GLU glu, Texture sunTexture) {
		this.gl = gl;
		this.glu = glu;
		this.sunTexture = sunTexture;
	}

	public void draw() {

		// Aplicar materiales
		float[] rgba = { 1f, 1f, 1f };
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 1.3f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL2.GL_DEPTH_BUFFER_BIT);

		// Aplicar textura
		sunTexture.enable(gl);
		sunTexture.bind(gl);
                
		gl.glPushName(6);
		angle = (angle + 0.7f) % 360f;

		gl.glPushMatrix();
		gl.glRotatef(angle, 0.2f, 0.9f, 0);

		GLUquadric sun = glu.gluNewQuadric();
		glu.gluQuadricTexture(sun, true);
		glu.gluQuadricDrawStyle(sun, GLU.GLU_FILL);
		glu.gluQuadricNormals(sun, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(sun, GLU.GLU_OUTSIDE);
		final float radius = IPlanets.SUN_DIAM;
		final int slices = 50;
		final int stacks = 50;
		glu.gluSphere(sun, radius, slices, stacks);
		glu.gluDeleteQuadric(sun);
                gl.glPopMatrix();
		gl.glPopName();
	}

}
