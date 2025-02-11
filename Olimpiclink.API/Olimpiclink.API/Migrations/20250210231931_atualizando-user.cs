using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Olimpiclink.API.Migrations
{
    /// <inheritdoc />
    public partial class atualizandouser : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<bool>(
                name: "Verificado_Email",
                table: "Users",
                type: "bit",
                nullable: false,
                defaultValue: false);

            migrationBuilder.AddColumn<string>(
                name: "number",
                table: "Users",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Verificado_Email",
                table: "Users");

            migrationBuilder.DropColumn(
                name: "number",
                table: "Users");
        }
    }
}
