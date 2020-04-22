import { Role } from './role.model';
export class User {
    public id: number;
    public username: string;
    public password: string;
    public name: string;
    public gender: string;
    public address: string;
    public phoneNumber: number;
    public email: string;
    public status: string;
    public avatarInBase64: string;
    public dateOfBirth: string;
    public roles: Role[];
}
