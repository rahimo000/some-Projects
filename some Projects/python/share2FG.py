import requests

# Replace with your actual access token
access_token = 'EAANwpbZCeRc0BO4ZBcXmmQhPAaKY0wcKTKfLsCZBTCN0LVk3IsUV9xmrJyr9ygZCmp15zTx2NYj8tEkCZBdZCZAu4VZActnhpNkHZCcDsulwgIxhmhSwe8gGScJ7ZCBukYl1X2fb58yV4qx2bqZBFd5S084we0hBeE2uVjxCtvvT4kbSYD2JhjNZAXheqARxD2qoy6nMsDevwUdwqNiMr4pDd6jsFnJvHwZDZD'

# ID of the group where you want to share the post
group_id = '809660713707041'

# ID of the post you want to share
post_id_to_share = '225687657070937'

# API endpoint for sharing a post to the group
share_url = f'https://graph.facebook.com/v17.0/{group_id}/feed'

# Your post data indicating the original post you want to share
post_data = {
    'message': 'Check out this interesting post!',
    'link': f'https://www.facebook.com/{post_id_to_share}'
}

# Parameters for the request
params = {
    'access_token': access_token
}

# Send the POST request to share the post
response = requests.post(share_url, data=post_data, params=params)

# Check the response
if response.status_code == 200:
    print("Post shared successfully!")
else:
    print("Post sharing failed. Response:", response.text)