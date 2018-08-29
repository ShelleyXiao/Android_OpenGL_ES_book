precision mediump float;

uniform samper2D u_TextureUint;

varying vec2 v_TextureCoordinates;

void main()
{
    gl_FragColor = texture2D(u_TextureUint, v_TextureCoordinates);
}