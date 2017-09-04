# Gotanda.mobile Slack invitation
[![CircleCI](https://circleci.com/gh/gotandamb/gotandamb-slack-invite/tree/master.svg?style=shield)](https://circleci.com/gh/gotandamb/gotandamb-slack-invite/tree/master)

## Requirements

- Java 8 or later
- IBM Bluemix account

## Getting started
### 1. Create Bluemix Account
### 2. Create Bluemix Application
### 3. Add environment variables

```
$ cf set-env YOUR_APP_NAME SLACK_API_TOKEN XXX
$ cf set-env YOUR_APP_NAME SLACK_TEAM XXX
```

### 4. Deploy

```
$ cf login -u username@example.com
$ cf push
```

## Development

```
$ ./sbt run
```

## License
MIT &copy; Pine Mizune
