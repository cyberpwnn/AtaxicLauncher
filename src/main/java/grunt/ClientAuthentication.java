package grunt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Proxy;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.mojang.authlib.Agent;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import grunt.ui.ProgressAuthenticating;

public class ClientAuthentication
{
	public enum AuthenticationResponse
	{
		SUCCESS,
		FAILED;
	}

	private String username;
	private String password;
	private String token;
	private String uuid;
	private String profileName;
	private String profileType;
	private PropertyMap profileSettings;
	private File authFile;
	private File uidfile;
	private String cid;
	private Map<String, Object> dat;

	public ClientAuthentication(File authFile, String username, String password) throws IOException, ClassNotFoundException
	{
		this(authFile);
		this.username = username;
		this.password = password;
	}

	public ClientAuthentication(File authFile) throws ClassNotFoundException, IOException
	{
		this.authFile = authFile;
		uidMagic();

		if(authFile.exists())
		{
			loadUserToken();
		}
	}

	private void uidMagic() throws IOException
	{
		this.uidfile = new File(authFile.getParent(), "uuid.kex");
		uidfile.getParentFile().mkdirs();

		if(uidfile.exists())
		{
			BufferedReader bu = new BufferedReader(new FileReader(uidfile));
			cid = bu.readLine();
			bu.close();
		}

		else
		{
			cid = UUID.randomUUID().toString().replaceAll("-", "-");
			PrintWriter pw = new PrintWriter(uidfile);
			pw.println(cid);
			pw.close();
		}

		System.out.println("Ataxic UID: " + cid);
	}

	public AuthenticationResponse authenticate()
	{
		System.out.println("Authenticating");
		ProgressAuthenticating authx = new ProgressAuthenticating();
		authx.setVisible(true);
		ProgressAuthenticating.panel.setProgress(3);

		try
		{
			YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, cid).createUserAuthentication(Agent.MINECRAFT);

			if(dat != null)
			{
				System.out.println("Signing into old session");
				auth.loadFromStorage(dat);
			}

			else
			{
				System.out.println("No session data, re-login");
				auth.setUsername(username);
				auth.setPassword(password);
			}

			ProgressAuthenticating.panel.setProgress(38);
			System.out.println("Authenticating...");
			auth.logIn();
			System.out.println("Login success");
			dat = auth.saveForStorage();
			System.out.println("Got " + dat.size() + " keys and values for session.");
			ProgressAuthenticating.panel.setProgress(46);
			token = auth.getAuthenticatedToken();
			uuid = auth.getSelectedProfile().getId().toString().replaceAll("-", "");
			profileName = auth.getSelectedProfile().getName();
			profileType = auth.getUserType().getName();
			profileSettings = auth.getSelectedProfile().getProperties();
			ProgressAuthenticating.panel.setProgress(66);
			System.out.println("Saving session data.");
			saveUserToken();
			ProgressAuthenticating.panel.setProgress(100);
			authx.setVisible(false);
			return AuthenticationResponse.SUCCESS;
		}

		catch(Exception e)
		{
			ProgressAuthenticating.panel.setProgress(0);
			authx.setVisible(false);
			return AuthenticationResponse.FAILED;
		}
	}

	public void saveUserToken() throws IOException
	{
		System.out.println("Saving Profile");
		FileOutputStream fos = new FileOutputStream(authFile);
		GZIPOutputStream gzo = new GZIPOutputStream(fos);
		ObjectOutputStream dos = new ObjectOutputStream(gzo);
		dos.writeObject(dat);
		dos.close();
	}

	@SuppressWarnings("unchecked")
	public void loadUserToken() throws IOException, ClassNotFoundException
	{
		System.out.println("Loading Profile");
		FileInputStream fin = new FileInputStream(authFile);
		GZIPInputStream gzi = new GZIPInputStream(fin);
		ObjectInputStream dis = new ObjectInputStream(gzi);
		dat = (Map<String, Object>) dis.readObject();
		dis.close();
	}

	public String getToken()
	{
		return token;
	}

	public String getUuid()
	{
		return uuid;
	}

	public String getProfileName()
	{
		return profileName;
	}

	public String getProfileType()
	{
		return profileType;
	}

	public PropertyMap getProfileSettings()
	{
		return profileSettings;
	}

	public File getAuthFile()
	{
		return authFile;
	}
}
