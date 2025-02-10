using Olimpiclink.API.Dto;
using Olimpiclink.API.Models;

namespace Olimpiclink.API.Repository.User
{
    public interface IUserRepository
    {
        Task<ResponseModel<UserModel>> CreateUser(UserCreateDto user);
        Task<ResponseModel<UserModel>> UpdateUser(UserModel user);
        Task<ResponseModel<UserModel>> DeleteUser(UserModel user);
        Task<ResponseModel<List<UserModel>>> ListUsers();
        Task<ResponseModel<UserModel>> GetUserById(int id);

        Task<ResponseModel<UserModel>> LoginUser(string username, string password);
    }
}
