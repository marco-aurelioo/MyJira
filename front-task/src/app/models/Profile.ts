export class Profile{

    userId?:  string;  
    name?:    string;
    avatar?:  string;
    characteristics?: string[];

    constructor(userId: string, name: string, avatar: string){
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
    }

}