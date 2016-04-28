package ControllerSubsystem;

import ModelSubsystem.*;

//Saner Turhaner

public class SoundManager 
{
	//Music
	Music music;
	Music dead;
	Music bomb;
	Music walk;
	
	//Constructor
    public SoundManager()
    {
        music = new Music("src/Sources/Sounds/music.wav");
        dead = new Music("src/Sources/Sounds/dead.wav");
        bomb = new Music("src/Sources/Sounds/bomb.wav");
    }
    
	//Necessary Method
	public void playGameMusic()
	{
		music.playMusic();
	}
	
	public void stopGameMusic()
	{
		music.stopMusic();
	}
	
	/*public void walkEffect()
	{
		walk.playAsSound();
	}*/
	
	public void bombEffect()
	{
		bomb.playAsSound();
	}
	
	public void deadEffect()
	{
		dead.playAsSound();
	}
		
}
