package kubitz.server.controllers;

/**
 * {LobbyJoinResponseMessage}
 * Author: Yaman Yağız Taşbağ
 * Version: {12/23/2018}
 */
public class LobbyJoinResponseMessage
{
    private boolean response;
    private String reason;

    public LobbyJoinResponseMessage() {}

    public LobbyJoinResponseMessage(boolean response, String reason)
    {
        this.response = response;
        this.reason = reason;
    }

    public boolean getResponse()
    {
        return response;
    }

    public void setResponse(boolean response)
    {
        this.response = response;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
}
