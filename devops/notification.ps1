function Send-SlackMessage {
   
    if ($($env:status) -eq "initial") {
        $icon = ":alert-blue:"
    }
    elseif ($($env:AGENT_JOBSTATUS) -eq "succeeded") {
        $icon = ":check:"
    }
    else {
        $icon = ":f:"
    }
    $body= @"
    {
        "username": "",
        "text": "$icon $($env:slackMessage)",
        "icon_emoji":""
    }
"@
          
    Invoke-WebRequest -Method Post -Uri $($env:Slack_WebHook) -Body $body -ContentType 'application/json'
}
Send-SlackMessage 