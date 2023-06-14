import {
  Avatar,
  Box,
  Container,
  Divider,
  List,
  ListItem,
  Paper,
  Stack,
  Table,
  Typography,
} from "@mui/material";
import { useLoaderData } from "react-router-dom";
import AuthService from "../services/auth";
import { stringToColor } from "../utils";
import UserDataService from "../services/user-data";

export async function loader() {
  const userDataList = (await UserDataService.getLeaderboard()).data;
  const user = AuthService.getCurrentUser();
  return [userDataList, user];
}

export default function LeaderBoard() {
  const [userDataList, user] = useLoaderData();
  console.log("list: ", userDataList);
  console.log("user: ", user);

  return (
    <Container maxWidth="lg" sx={{ padding: 1 }}>
      <Paper>
        <Typography variant="h4" textAlign="center" sx={{ padding: 1 }}>
          Best Learners
        </Typography>
        <Divider />
        {userDataList.length > 0 && (
          <List>
            {userDataList.map((userData) => (
              <React.Fragment key={userData.userId}>
                <ListItem
                  sx={
                    user && user.userId === userData.userId
                      ? { bgcolor: "selected.main" }
                      : {}
                  }
                >
                  <Stack width={1} spacing={0.5}>
                    <Stack
                      direction="row"
                      alignItems="center"
                      spacing={1}
                      width={1}
                    >
                      <Typography>{`${userData.position}.`}</Typography>
                      <Stack
                        direction="row"
                        justifyContent="left"
                        alignItems="center"
                        spacing={1}
                        sx={{ flexGrow: 1 }}
                      >
                        <Avatar
                          sx={{ bgcolor: stringToColor(userData.username) }}
                        >
                          {userData.username[0].toUpperCase()}
                        </Avatar>
                        <Typography noWrap>{userData.username}</Typography>
                      </Stack>
                      <Typography>{`${userData.exp} exp`}</Typography>
                    </Stack>
                  </Stack>
                </ListItem>
                <Divider />
              </React.Fragment>
            ))}
          </List>
        )}
      </Paper>
    </Container>
  );
}
