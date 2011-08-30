package cx.mccormick.pddroidparty;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;

import android.util.Log;
import android.graphics.Picture;

import com.larvalabs.svgandroid.SVGParser;

public class SVGRenderer {
	// used for modification (e.g. interpolation) of the original svg
	SVGManipulator original = null;
	// cached image so we don't keep regenerating it every frame
	Picture cached = null;
	
	public SVGRenderer(File f) {
		original = new SVGManipulator(f);
	}
	
	public static SVGRenderer getSVGRenderer(PdDroidPatchView parent, String name) {
		// reads the SVG string from a file if the file with with name exists
		// returns null if the file with name does not exist	
		File f = new File(parent.app.getPatchFile().getParent() + "/" + name + ".svg");
		if (f.exists() && f.canRead() && f.isFile()) {
			return new SVGRenderer(f);
		}
		return null;
	}
	
	// get a rendered version of the svg
	public Picture getPicture() {
		if (cached == null) {
			cached = SVGParser.getSVGFromString(original.toString()).getPicture();
		}
		return cached;
	}
	
	// interpolate between two paths in the SVG, making the second one invisible
	public SVGRenderer interpolate(String startid, String endid, float amount) {
		// de-cache
		cached = null;
		return this;
	}
}