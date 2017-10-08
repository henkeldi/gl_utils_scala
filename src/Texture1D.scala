
import org.lwjgl.opengl.GL45._
import java.nio._

class Texture1D(
    levels: Int, 
    internalformat: Int,
    width: Int,
) extends ITexture 
{
  glTextureStorage1D(id, levels, internalformat, width) 

  
  def sub_image(level: Int, xoffset: Int, yoffset: Int,  width: Int, height: Int, data_format: Int, data_type: Int, pixels: ByteBuffer) {
    glTextureSubImage1D(id, level, xoffset, width, data_format, data_type, pixels)
  }

  
  def sub_image(level: Int, xoffset: Int, yoffset: Int,  width: Int, height: Int, data_format: Int, data_type: Int, pixels: IntBuffer) {
    glTextureSubImage1D(id, level, xoffset, width, data_format, data_type, pixels)
  }
  
  
  def sub_image(level: Int, xoffset: Int, yoffset: Int,  width: Int, height: Int, data_format: Int, data_type: Int, pixels: FloatBuffer) {
    glTextureSubImage1D(id, level, xoffset, width, data_format, data_type, pixels)
  }

  
  def sub_image(level: Int, xoffset: Int, yoffset: Int,  width: Int, height: Int, data_format: Int, data_type: Int, pixels: DoubleBuffer) {
    glTextureSubImage1D(id, level, xoffset, width, data_format, data_type, pixels)
  }
  
}