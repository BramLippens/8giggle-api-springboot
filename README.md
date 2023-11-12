# 8giggle-api-springboot
8giggle is a fun and easy way to discover and share the latest memes on the internet. Whether you are looking for humor, sarcasm, wtf, or woah, you will find it all on 8giggle. You can also create your own memes and upload them to the app, or browse through thousands of memes from other users. 8giggle lets you upvote, downvote, comment, and share your favorite memes with your friends. You can also follow the most popular meme trends and topics, such as one piece, trump, elon musk, and more. 8giggle is the ultimate meme app for anyone who loves to laugh and have fun. Download 8giggle today and join the largest meme community on the internet.

### Railway

Bring your code, we'll handle the rest. Made for any language, for projects big and small. [Railway](https://railway.app/)
is the cloud that takes the complexity out of shipping software.

Create a new empty project in Railway and start by creating a PostgreSQL database. Once you have that created you can create
a new project from GitHub. You can use the following environment variables based on the database you just created.

```properties
spring_profiles_active=prod
PROD_DB_HOST=monorail.proxy.rlwy.net
PROD_DB_PORT=47167
PROD_DB_NAME=railway
PROD_DB_PASSWORD=4cFCB55cAgb16eedG1ff5Cgb6B6-Fc*g
PROD_DB_USERNAME=postgres
PROD_JWT_SECRET=092CA2FB0CA2B7EA472DAFA041395B2F06C9E090818EF43DA537922236BB960A
CLOUDINARY_SECRET=clZwIGa_rGLm4eLjXY0pDMiS_tk
CLOUDINARY_KEY=533978228527723
CLOUDINARY_NAME=duu3f7jg1
```

You don't need GitHub Actions or any type of pipeline for this setup because Railway handles this for you. Simply push your code to GitHub
and a new build and deploy will be triggered. If you want to disable this functionality you can from the settings of your project
on Railway. 
