
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL12.GL_TEXTURE_WRAP_R
import org.lwjgl.opengl.GL45._

class ITexture(){
  
  val id = glCreateTextures(1)

  def set_filter(min_filter: Int, mag_filter: Int) {
    glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, min_filter)
    glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, mag_filter)
  }
  
  def set_wrap(wrap_s: Int, wrap_t: Int = -1, wrap_r: Int = -1){
    glTextureParameteri(id, GL_TEXTURE_WRAP_S, wrap_s)
    if (wrap_t != -1)
      glTextureParameteri(id, GL_TEXTURE_WRAP_T, wrap_t)
    if (wrap_r != -1)
      glTextureParameteri(id, GL_TEXTURE_WRAP_R, wrap_r)
  }
  
  def generate_mipmap(){
    glGenerateTextureMipmap(id) 
  }

  def delete() {
    glDeleteTextures(id) 
  }
    
}