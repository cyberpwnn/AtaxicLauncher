package grunt;

public class URLX
{
	public static final String FORGE_VERSION = "14.23.5.2768";
	public static final String MUS = "https://raw.githubusercontent.com/ShadowRealmMC/ShadowBeast/master/run.wav";
	public static final String PDS = "https://raw.githubusercontent.com/ShadowRealmMC/ShadowBeast/master/patches/P.";
	public static final String PDS_META = "https://raw.githubusercontent.com/ShadowRealmMC/ShadowBeast/master/patch.mda";
	public static final String LIB_TTS = "{\"downloads\":{\"artifact\":{\"path\":\"com/mojang/text2speech/1.10.3/text2speech-1.10.3.jar\",\"sha1\":\"48fd510879dff266c3815947de66e3d4809f8668\",\"size\":11055,\"url\":\"https://libraries.minecraft.net/com/mojang/text2speech/1.10.3/text2speech-1.10.3.jar\"},\"classifiers\":{\"natives-linux\":{\"path\":\"com/mojang/text2speech/1.10.3/text2speech-1.10.3-natives-linux.jar\",\"sha1\":\"ab7896aec3b3dd272b06194357f2d98f832c0cfc\",\"size\":7833,\"url\":\"https://libraries.minecraft.net/com/mojang/text2speech/1.10.3/text2speech-1.10.3-natives-linux.jar\"},\"natives-windows\":{\"path\":\"com/mojang/text2speech/1.10.3/text2speech-1.10.3-natives-windows.jar\",\"sha1\":\"84a4b856389cc4f485275b1f63497a95a857a443\",\"size\":81217,\"url\":\"https://libraries.minecraft.net/com/mojang/text2speech/1.10.3/text2speech-1.10.3-natives-windows.jar\"},\"sources\":{\"path\":\"com/mojang/text2speech/1.10.3/text2speech-1.10.3-sources.jar\",\"sha1\":\"404339fe43d1011ee046a249b0ec7ae9ce04a834\",\"size\":4632,\"url\":\"https://libraries.minecraft.net/com/mojang/text2speech/1.10.3/text2speech-1.10.3-sources.jar\"}}},\"extract\":{\"exclude\":[\"META-INF/\"]},\"name\":\"com.mojang:text2speech:1.10.3\",\"natives\":{\"linux\":\"natives-linux\",\"windows\":\"natives-windows\"}}";
	public static final String IFORGE = "{\"id\":\"1.12.2-forge1.12.2-" + FORGE_VERSION + "\",\"time\":\"2018-06-01T07:08:01+0000\",\"releaseTime\":\"1960-01-01T00:00:00-0700\",\"type\":\"release\",\"minecraftArguments\":\"--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userType ${user_type} --tweakClass net.minecraftforge.fml.common.launcher.FMLTweaker --versionType Forge\",\"mainClass\":\"net.minecraft.launchwrapper.Launch\",\"inheritsFrom\":\"1.12.2\",\"jar\":\"1.12.2\",\"logging\":{},\"libraries\":[" + LIB_TTS + ",{\"name\":\"net.minecraftforge:forge:1.12.2-" + FORGE_VERSION + "\",\"url\":\"http://files.minecraftforge.net/maven/\"},{\"name\":\"net.minecraft:launchwrapper:1.12\",\"serverreq\":true},{\"name\":\"org.ow2.asm:asm-all:5.2\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"2ea49e08b876bbd33e0a7ce75c8f371d29e1f10a\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"jline:jline:2.13\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"2d9530d0a25daffaffda7c35037b046b627bb171\"],\"serverreq\":true,\"clientreq\":false},{\"name\":\"com.typesafe.akka:akka-actor_2.11:2.3.3\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"ed62e9fc709ca0f2ff1a3220daa8b70a2870078e\",\"25a86ccfdb6f6dfe08971f4825d0a01be83a6f2e\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"com.typesafe:config:1.2.1\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"f771f71fdae3df231bcd54d5ca2d57f0bf93f467\",\"7d7bc36df0989d72f2d5d057309675777acc528b\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"org.scala-lang:scala-actors-migration_2.11:1.1.0\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"dfa8bc42b181d5b9f1a5dd147f8ae308b893eb6f\",\"8c9aaeeb68487ca519411a14068e1b4d69739207\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"org.scala-lang:scala-compiler:2.11.1\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"56ea2e6c025e0821f28d73ca271218b8dd04926a\",\"1444992390544ba3780867a13ff696a89d7d1639\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"org.scala-lang.plugins:scala-continuations-library_2.11:1.0.2\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"87213338cd5a153a7712cb574c0ddd2edfee0386\",\"0b4c1bf8d48993f138d6e10c0c144e50acfff581\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"org.scala-lang.plugins:scala-continuations-plugin_2.11.1:1.0.2\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"1f7371605d4ba42aa26d3443440c0083c587b4e9\",\"1ea655dda4504ae0a367327e2340cd3beaee6c73\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"org.scala-lang:scala-library:2.11.1\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"0e11da23da3eabab9f4777b9220e60d44c1aab6a\",\"1e4df76e835201c6eabd43adca89ab11f225f134\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"org.scala-lang:scala-parser-combinators_2.11:1.0.1\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"f05d7345bf5a58924f2837c6c1f4d73a938e1ff0\",\"a1cbbcbde1dcc614f4253ed1aa0b320bc78d8f1d\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"org.scala-lang:scala-reflect:2.11.1\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"6580347e61cc7f8e802941e7fde40fa83b8badeb\",\"91ce0f0be20f4a536321724b4b3bbc6530ddcd88\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"org.scala-lang:scala-swing_2.11:1.0.1\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"b1cdd92bd47b1e1837139c1c53020e86bb9112ae\",\"d77152691dcf5bbdb00529af37aa7d3d887b3e63\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"org.scala-lang:scala-xml_2.11:1.0.2\",\"url\":\"http://files.minecraftforge.net/maven/\",\"checksums\":[\"7a80ec00aec122fba7cd4e0d4cdd87ff7e4cb6d0\",\"62736b01689d56b6d09a0164b7ef9da2b0b9633d\"],\"serverreq\":true,\"clientreq\":true},{\"name\":\"lzma:lzma:0.0.1\",\"serverreq\":true},{\"name\":\"net.sf.jopt-simple:jopt-simple:5.0.3\",\"serverreq\":true},{\"name\":\"java3d:vecmath:1.5.2\",\"clientreq\":true,\"serverreq\":true},{\"name\":\"net.sf.trove4j:trove4j:3.0.3\",\"clientreq\":true,\"serverreq\":true},{\"name\":\"org.apache.maven:maven-artifact:3.5.3\",\"url\":\"http://files.minecraftforge.net/maven/\",\"serverreq\":true,\"clientreq\":true}]}";
	public static final String VERSION_META = "https://launchermeta.mojang.com/mc/game/version_manifest.json";
}
