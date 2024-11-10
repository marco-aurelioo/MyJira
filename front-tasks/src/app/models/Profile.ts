export class Profile{

id?:   String;  
username?: String;
image?: String;

    constructor(id: string, userName: string, image: string){
        this.id = id;
        this.username = userName;
        this.image = image;
    }

}