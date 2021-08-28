package Interfaces;



public interface IThrowerListener{
    void onThrow(IThrower thrower, IThrowable throwable);
    void onThrowStart(IThrower thrower);
    void onThrowStop(IThrower thrower);
}